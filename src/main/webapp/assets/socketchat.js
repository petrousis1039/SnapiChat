// Websocket Endpoint url
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + window.location.host + webCtx + "/chatApp";

//openshift workaround
if(window.location.host.indexOf("rhcloud") > -1 ) {
    endPointURL = "ws://snapichat-dreamteamteicm.rhcloud.com:8000/SnapiChat/chatApp";
}

var chatClient = null;
var active = "";

var uid;
var username;
    
function connect (uid, username) {
    chatClient = new WebSocket(endPointURL + "/" + uid);
    
    this.uid = uid;
    this.username = username;
    
    chatClient.onopen = function (event) {
        
    };
    
    chatClient.onmessage = function (event) {
        console.log(event.data);
            
        var jsonObj = JSON.parse(event.data);
        console.log(jsonObj);
            
        var action = jsonObj.action;
        
        if (action === "LIST") {
            $('#online-users-list').empty();
            
            for (var i = 0; i < jsonObj.users.length; i++) {
                var user = jsonObj.users[i];
                
                if (user.uid === uid) {
                    continue;
                }

                var li = $('<li>', {}).appendTo('#online-users-list');
                
                var link = $('<a>', {
                    text: user.username,
                    class: 'open-tab',
                    href: '#' + user.uid,
                    'data-uid': user.uid,
                    click: function(e) {
                        e.preventDefault();
                        var check = $("#myTab li a[data-uid='" + $(this).attr('data-uid') + "']");
                        
                        if (check.length !== 0) {
                            return;
                        }

                        var li = $('<li>', {}).appendTo('#myTab');
                        
                        var a = $('<a>', {
                            text: $(this).text(),
                            href: '#' + $(this).attr('data-uid'),
                            'data-toggle': 'tab',
                            'data-uid': $(this).attr('data-uid'),
                            click: function(e) {
                                e.preventDefault();
                                    $(this).tab('show');
                                }
                        }).appendTo(li);
                        
                        a.on('shown.bs.tab', function (e) {
                            active = $(e.target).attr('data-uid');
                        });
                        
                        var div = $('<div>', {
                            class: "tab-pane fade",
                            id: $(this).attr('data-uid')
                        }).appendTo('#tab-content-wrap');
                        
                        $('<ul>', {
                            class: "messages panel message-area"
                        }).appendTo(div);
                            
                        return false;
                    }
                }).appendTo(li);
                
                var addBtn = $('<a>', {
                    href: "story/" + user.uid,
                    class: "btn btn-primary btn-story"
                }).appendTo(li);
                
                $('<i>', {
                    class: "glyphicon glyphicon-picture"
                }).appendTo(addBtn);
                
                $('<img>', {
                    src: "profileImage?uid=" + user.uid,
                    width: 50
                }).prependTo(link);
            }
            
        } else if (action === "MESSAGE") {
            console.log("message");
            var messagesArea = null;
            
            if (jsonObj.from === uid) {
                messagesArea = $('#' + jsonObj.to).children('.messages')[0];
            } else {
                messagesArea = $('#' + jsonObj.from).children('.messages')[0];
                console.log(messagesArea);
                
                if (!messagesArea) {
                    var li = $('<li>', {}).appendTo('#myTab');
                    
                    var a = $('<a>', {
                        text: jsonObj.from_username,
                        href: '#' + jsonObj.from,
                        'data-toggle': 'tab',
                        'data-uid': jsonObj.from,
                        click: function(e) {
                            e.preventDefault();
                                $(this).tab('show');
                            }
                    }).appendTo(li);
                    
                    a.on('shown.bs.tab', function (e) {
                        active = $(e.target).attr('data-uid');
                                console.log(active);
                        });
                    
                    var div = $('<div>', {
                        class: "tab-pane fade",
                        id: jsonObj.from
                    }).appendTo('#tab-content-wrap');
                    
                    $('<ul>', {
                        class: "messages panel message-area"
                    }).appendTo(div);
                }
                
                messagesArea = $('#' + jsonObj.from).children('.messages')[0];
            }
            
            if(jsonObj.img !== '') {
                var msg = $('<li>', {
                    text: jsonObj.from_username + ": "
                }).appendTo(messagesArea);
                
                var a = $('<a>', {
                    href: jsonObj.img,
                    'data-gallery': ''
                }).appendTo(msg);
                
                var img = $('<img>', {
                    src: jsonObj.img,
                    width: 50,
                    height: 'auto'
                }).appendTo(a);
                
                var addForm = $('<form>', {
                    action: "addToStory",
                    method: "POST",
                    class: "add-to-story",
                    target: "_blank"
                }).appendTo(msg);
                
                $('<input>', {
                    type: "submit",
                    class: "btn btn-sm",
                    value: "Add to Story"
                }).appendTo(addForm);
                
                $('<input>', {
                    type: "hidden",
                    name: "img",
                    value: jsonObj.img
                }).appendTo(addForm);
                
                $('<input>', {
                    type: "hidden",
                    name: "uid",
                    value: uid
                }).appendTo(addForm);
                
                if(jsonObj.from !== uid) {
                    msg.addClass('other');
                }
            }
            
            if(jsonObj.message !== '') {
                var msg = $('<li>', {
                    text: jsonObj.from_username + ": " + jsonObj.message
                }).appendTo(messagesArea);

                if(jsonObj.from !== uid) {
                    msg.addClass('other');
                }
            }
            
            messagesArea.scrollTop = messagesArea.scrollHeight;
        
        } else if (action === "JOIN" || action === "CLOSE") {
            console.log("user action " + jsonObj.user);
        }
    };
}

function disconnect () {
    chatClient.close();
}

function sendMessage() {
    var inputElement = document.getElementById("messageInput");
    var message = inputElement.value.trim();

    var file = $('#snapi-pic').prop('files')[0];
    if(!file) {
        var jsonObj = {
            'action': 'MESSAGE',
            'from': uid,
            'to': active,
            'message': message,
            'img': ''
        };
        console.log(JSON.stringify(jsonObj));
        chatClient.send(JSON.stringify(jsonObj));
        
        inputElement.value = "";
        inputElement.focus();
    }
    var reader = new FileReader();
    // Sends the result of the file read as soon as the reader has
    // completed reading the image file.
    reader.onloadend = function(){
        var jsonObj = {
            'action': 'MESSAGE',
            'from': uid,
            'to': active,
            'message': message,
            'img': reader.result
        };
        console.log(JSON.stringify(jsonObj));
        chatClient.send(JSON.stringify(jsonObj));
    };

    // Make sure the file exists and is an image
    if(file.type.match("image")){
        reader.readAsDataURL(file);
    }

    inputElement.value = "";
    inputElement.focus();
    
    resetFormElement($("#snapi-pic"));
}

function resetFormElement(e) {
    e.wrap('<form>').closest('form').get(0).reset();
    e.unwrap();

    // Prevent form submission
    //e.preventDefaults();
}
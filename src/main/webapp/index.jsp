<html>

<head>
    <title>Star page</title>
    <script type="text/javascript">
        var flag = null;
        var socket = new WebSocket("ws://localhost:8080/app");
        socket.onopen = function () {
//            alert("connected");
            flag = window.setInterval(sendList, 5000);
        };
        socket.onclose = function (event) {
            window.clearInterval(flag);
            if (event.wasClean) {
                alert('Closed');
            } else {
                alert('Error');
            }
        };
        socket.onmessage = function (event) {
            var message = event.data;
            var message_array = message.split(":");
            if (message_array[0] == "list") {
                document.getElementById("active").value = message_array[1];
            } else {
                var text = document.getElementById("output").value;
                text += "\n" + message_array[0] + " : " + message_array[1];
                document.getElementById("output").value = text;
            }
        };

        function sendList() {
            socket.send("list:");
        }

        function send() {
            var input = document.getElementById('input').value;
            socket.send(input);
        }

    </script>
</head>
<body>
<h2>WebSocket</h2>
<form>
    <textarea rows="10" cols="45" id="active"></textarea>
    <textarea rows="10" cols="45" id="output"></textarea>
    <input type="text" id="input"/>
    <input type="button" onclick="send()" name="Send" value="Send"/>
</form>
</body>
</html>

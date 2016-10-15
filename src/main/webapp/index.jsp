<html>

<head>
    <title>Star page</title>
    <script type="text/javascript">
        var socket = new WebSocket("ws://localhost:8080/app");
        socket.onopen = function () {
            alert("connected");
        };
        socket.onclose = function (event) {
            if (event.wasClean) {
                alert('close');
            } else {
                alert('error');
            }
        };
        socket.onmessage = function (event) {
            var message = event.data;
            var text = document.getElementById("output").value;
            text += "\n" + message;
            document.getElementById("output").value = text;
        };

        function send() {
            var input = document.getElementById('input').value;
            socket.send(input);
        }

    </script>
</head>
<body>
<h2>WebSocket</h2>
<form>
    <textarea id="output"></textarea>
    <input type="text" id="input"/>
    <input type="button" onclick="send()" name="Send" value="Send"/>
</form>
</body>
</html>

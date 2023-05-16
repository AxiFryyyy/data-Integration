var url = "http://localhost:8080"
function collect(ToCollect) {
    fetch(url + '/Home/collect/' + ToCollect)
        .then(function(response) {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(function(data) {
            console.log(data);
        })
        .catch(function(error) {
            console.log('There has been a problem with your fetch operation: ', error.message);
        });
}

let socket = new WebSocket('ws://localhost:8080/websocket');
socket.onmessage = function(event) {
    console.log('Received message: ' + event.data);
    let obj = JSON.parse(event.data);
    let msg = obj["msg"];
    let textArea = document.getElementById('log');
    textArea.textContent = msg;
};

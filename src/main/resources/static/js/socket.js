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
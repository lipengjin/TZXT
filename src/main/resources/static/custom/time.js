var ShowTime = function () {

    return {
        initTime: function () {
            console.log('init time');
            setInterval(function () {
                var curTime = new Date();
                $("div#currentTime").html(curTime.toLocaleString());
            }, 1000);
        }
    }
}();
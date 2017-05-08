var Lock = function () {

    return {
        //main function to initiate the module
        init: function () {

             $.backstretch([
		        "../static/media/bg/1.jpg",
    		    "../static/media/bg/2.jpg",
    		    "../static/media/bg/3.jpg",
    		    "../static/media/bg/4.jpg"
		        ], {
		          fade: 1000,
		          duration: 8000
		      });
        }

    };

}();
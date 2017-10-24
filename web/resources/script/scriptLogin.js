function position_contentLogin() {
	jQuery(".contentLogin").css({
        left: ( jQuery(window).width() - jQuery(".contentLogin").width() )/2,
        top: ( jQuery(window).height() - jQuery(".contentLogin").height() )/2
   });
}

jQuery(document).ready(function() {
	position_contentLogin();
});

jQuery(window).resize(function() {
	position_contentLogin();
});
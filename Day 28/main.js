var number = 0;

$(document).ready(function() {
	var increment = $("#increment");
	var decrement = $("#decrement");
	var reset = $("#reset");
	var textbox = $("#textbox");
	var manualchange = $("#manualchange");
	
	textbox.text(number);
	
	
	manualchange.click(function() {
		var guess = Number( $("#inputbox").val() );
		number += guess;
		
		if(number%2 === 0){
			textbox.addClass("italic");
			if(number === 0) {
				textbox.removeClass("italic");
			}
		} else {
			textbox.removeClass("italic");
		}
		if(number < 8) {
		textbox.text(number);
		} else {
		textbox.addClass("red");
		textbox.text(number);
		}
		
		if(number > 8) {
		textbox.text(number);
		} else {
		textbox.removeClass("red");
		textbox.text(number);
		}
	});
	
	increment.click(function() {
    number++;
		if(number%2 === 0){
			textbox.addClass("italic");
			if(number === 0) {
				textbox.removeClass("italic");
			}
		} else {
			textbox.removeClass("italic");
		}
		if(number < 8) {
		textbox.text(number);
		} else {
		textbox.addClass("red");
		textbox.text(number);
		}
	});
	
	
	
	decrement.click(function() {
    number--;
		if(number%2 === 0){
			textbox.addClass("italic");
			if(number === 0) {
				textbox.removeClass("italic");
			}
		} else {
			textbox.removeClass("italic");
		}
		if(number > 8) {
		textbox.text(number);
		} else {
		textbox.removeClass("red");
		textbox.text(number);
		}
	});
	
	reset.click(function() {
		textbox.removeClass("italic");
		textbox.removeClass("red");
		textbox.removeClass("green");
		number = 0;
		textbox.text(number);
	});
	
});
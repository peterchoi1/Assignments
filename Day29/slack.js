$(document).ready(function() {
	
	function postMessage(msg, myChannel) {
	$.ajax("https://slack.com/api/chat.postMessage", {
	 data: {
	   token: getSlackToken(),
	   channel: myChannel,
	   text: msg,
	   username: "yayayayayayayaya"
	 },
	 method: "POST"
	})

	};
	
	
	
	
	$.ajax("https://slack.com/api/emoji.list", {
    data: {
      token: getSlackToken()
    },
	method: "GET"
	}).then(function(emojiList){
		//console.log(emojiList);
		$("#emojiP").append("<img src=" + emojiList.emoji.bowtie + ">");
		console.log(emojiList.emoji.bowtie);
	});
	

	
	
$("#post").click(function() {
	var text = $("#textbox").val();
	var channel = $('input[name="channel"]:checked').val();
	postMessage(text,channel);
	$("#textbox").val('');
  
})


$('#textbox').keypress(function (e) {
 var key = e.which;
 if(key == 13)  // the enter key code
  {
    $("#post").click();
    return false;  
  }
});  

	
	$.ajax("https://slack.com/api/channels.list", {
    data: {
      token: getSlackToken()
    },
  method: "POST"
  }).then(function(channelsList){
      for(var count = 0; count < channelsList.channels.length; count++)
      {
          var newChannelBtn = $("<input type='radio' name='channel' value="+channelsList.channels[count].name+"> " + channelsList.channels[count].name + "</input><br>");
          $("#editedP").append(newChannelBtn);
      }
      console.log(channelsList);
  });

  
  $.ajax("https://slack.com/api/groups.list", {
    data: {
      token: getSlackToken()
    },
  method: "POST"
  }).then(function(groupList){
      for(var count = 0; count < groupList.groups.length; count++)
      {
          var newChannelBtn = $("<input type='radio' name='channel' value="+groupList.groups[count].name+"> " + groupList.groups[count].name + "</input><br>");
          $("#editedPrivate").append(newChannelBtn);
      }
      console.log(groupList);
  });
  
	
});
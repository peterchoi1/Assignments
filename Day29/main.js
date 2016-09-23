var token = "64a1ba4a252a7c1f1cbcf55d11ab660d74efaeb1";

$(document).ready(function(){
  $.ajax("https://api.github.com/users/jamesdabbs/repos", {
    data: {
      sort: "updated",
      direction: "desc"
    }
  }).then(function(result) {
    console.log("response is done");
    // console.log("the data was", result);
    // console.log(result[0].name);

    for (var i=0; i < result.length; i++) {
      // name is result[i].name
      var item = $("<li>" + result[i].name + "</li>");

      var btn = $("<button>Save</button>");
      btn.data("name", result[i].name);

      item.append( btn );

      $("#repos").append( item );
    }

    $("button").click(function() {
      var text = $("#desc").val();
      var repo = $(this).data("name");

      console.log("updating", repo, text);
      setRepoDescription(text, repo);
    })
  })

  console.log("Here");
});

function setRepoDescription(newDesc, repoName) {
  var url = "https://api.github.com/repos/jamesdabbs/" + repoName;
  url += "?access_token=" + token;

  var requestData = {
    name: repoName,
    description: newDesc
  }

  $.ajax(url, {
    data: JSON.stringify(requestData),
    method: "PATCH"
  })
}
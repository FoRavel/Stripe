<!doctype html>
<html lang="fr">
  <head>
    <meta charset="utf-8">
    <title>Titre de la page</title>


  </head>
  <body>
    <p id="para">125</p>
    <script type="text/javascript">
      document.write("Bonjour tout le monde!");
      function getToken2(){
        fetch("http://192.168.0.17/fundraising/STRIPE/fetch_api.php")
          .then((response) => response.json())
          .then((responseJson) => {
            alert(responseJson);
          })
      }
      async function getToken(){
        const proxyurl = "https://immense-anchorage-73038.herokuapp.com/";
        const url = "http://192.168.0.17/fundraising/STRIPE/fetch_api.php";
        const response = await fetch(url);
        const responseJson = await response.json();
        alert(responseJson);
      }
getToken();

    </script>
  </body>
</html>

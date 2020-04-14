<?php

require_once('vendor/autoload.php');

\Stripe\Stripe::setApiKey("sk_test_XLk0yslE9DozPgtmXC4wWk8r");

$token = $_POST['stripeToken'];
//$memorizeCard = $_POST['checkboxState'];
//$amount = (int)$_POST["amount"];


/*
Paiement par carte bancaire en utilisant le jeton qui lui fait référence.
Le jeton est  à usage unique. Cela contraint l'utilisateur à saisir
ses informations de carte à chaque nouveau paiement pour générer un nouveau jeton.

$charge = \Stripe\Charge::create([
    'amount' => 999,
    'currency' => 'eur',
    'source' => $token,
]);

*/


//enregistrer les informations de la carte bancaire représentée par son jeton,
//dans un objet Customer

  $customer = \Stripe\Customer::create([
      'source' => $token,
      'email' => 'paying.user@example.com',
  ]);


  /*
  L'objet Customer contient désormais les données de carte bancaire, pour effectuer un
  paiement, on peut donc ré-utiliser cet objet à l'infini. Cela évite à l'utilisateur
  de saisir ses informations de carte bancaire à chaque nouveau paiement.

  $charge = \Stripe\Charge::create([
      'amount' => $amount,
      'currency' => 'eur',
      'customer' => $customer->id,
  ]);
*/






/*Paiement vers le compte Stripe d'un autre utilisateur
$charge = \Stripe\Charge::create([
  "amount" => 1000,
  "currency" => "eur",
  "source" => $token,
  "destination" => [
    "account" => "acct_1DkUy3KpP1jHDfQa",
  ],
]);
*/

echo json_encode($customer->id);
?>

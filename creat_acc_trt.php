<?php

require_once('vendor/autoload.php');

\Stripe\Stripe::setApiKey("sk_test_XLk0yslE9DozPgtmXC4wWk8r");


$token = \Stripe\Token::create([
  "account" => [
    "legal_entity" => [
      "first_name" => "Jane",
      "last_name" => "Doe",
    ],
    "tos_shown_and_accepted" => true
  ]
]);


$acct = \Stripe\Account::create([
    "country" => "FR",
    "type" => "custom",
    "account_token" => $token->id,
]);



echo json_encode($acct->id);

?>

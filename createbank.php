<?php

require_once('vendor/autoload.php');

\Stripe\Stripe::setApiKey("sk_test_XLk0yslE9DozPgtmXC4wWk8r");

$bank = \Stripe\Token::create([
  "bank_account" => [
    "country" => "FR",
    "currency" => "eur",
    "account_holder_name" => "Jenny Rosen",
    "account_holder_type" => "individual",

    "account_number" => "FR89370400440532013000"
  ]
]);

printf($bank);
 ?>

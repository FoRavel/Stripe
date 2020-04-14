<?php

require_once('vendor/autoload.php');

\Stripe\Stripe::setApiKey("sk_test_XLk0yslE9DozPgtmXC4wWk8r");

$balance = \Stripe\Balance::retrieve(
  ["stripe_account" => "acct_1DkUy3KpP1jHDfQa"
]);

printf($balance);

 ?>

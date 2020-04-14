<?php

require_once('vendor/autoload.php');

\Stripe\Stripe::setApiKey("sk_test_XLk0yslE9DozPgtmXC4wWk8r");

$account = \Stripe\Account::retrieve("acct_1DkUy3KpP1jHDfQa");
$account->payout_schedule = ["delay_days" => 7, "interval" => 'daily'];
$account->save();


 ?>

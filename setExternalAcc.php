<?php
require_once('vendor/autoload.php');

\Stripe\Stripe::setApiKey("sk_test_XLk0yslE9DozPgtmXC4wWk8r");
$account = \Stripe\Account::retrieve("acct_1DkUy3KpP1jHDfQa");
$account->external_accounts->create([
    "external_account" => "btok_1DkV49CHi2hgOXJOEr2k83uZ", //obtained from createbank.php
]);
 ?>

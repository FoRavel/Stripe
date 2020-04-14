PAS UTILE
<?php
/*
require_once('vendor/autoload.php');



\Stripe\Stripe::setApiKey("sk_test_XLk0yslE9DozPgtmXC4wWk8r");

$acct = \Stripe\Account::create([
    "country" => "FR",
    "type" => "custom"
]);

printf($acct);
*/
?>

<form class="my-form" action="creat_acc_trt.php" method="post">
  <input type="hidden" name="token" id="token">
  <label>
    <span>First Name</span>
    <input class="inp-first-name" name="first_name">
  </label>
  <label>
    <span>Last Name</span>
    <input class="inp-last-name" name="last_name">
  </label>
  <fieldset>
    <legend>Address</legend>
    <label>
      <span>Street Address Line 1</span>
      <input class="inp-street-address1" name="street_address1">
    </label>
    <label>
      <span>City</span>
      <input class="inp-city" name="city">
    </label>
    <label>
      <span>State</span>
      <input class="inp-state" name="state">
    </label>
    <label>
      <span>Postal Code</span>
      <input class="inp-zip" name="zip">
    </label>
  </fieldset>
  <button>Submit</button>
</form>
<script src="https://js.stripe.com/v3/"></script>
<script>
const stripe = Stripe('pk_test_Q4mqDHJenzlISLNLULm55q3b');
const myForm = document.querySelector('.my-form');
myForm.addEventListener('submit', handleForm);

async function handleForm(event) {
  event.preventDefault();

  const result = await stripe.createToken('account', {
    legal_entity: {
      first_name: document.querySelector('.inp-first-name').value,
      last_name: document.querySelector('.inp-last-name').value,
      address: {
        line1: document.querySelector('.inp-street-address1').value,
        city: document.querySelector('.inp-city').value,
        state: document.querySelector('.inp-state').value,
        postal_code: document.querySelector('.inp-zip').value,
      },
    },
    tos_shown_and_accepted: true,
  });

  if (result.token) {
    document.querySelector('#token').value = result.token.id;
    myForm.submit();
  }
}
</script>

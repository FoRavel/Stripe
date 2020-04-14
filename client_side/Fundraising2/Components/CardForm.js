import React from 'react';
import {View, TextInput, Button} from 'react-native';
import stripe from 'tipsi-stripe';

stripe.setOptions({
publishableKey: 'pk_test_Q4mqDHJenzlISLNLULm55q3b',

androidPayMode: 'test', // Android only
});

class CardForm extends React.Component {
  constructor(props){
      super(props)
      this.state = {

      }
      this.params ={
        number: "",
        expMonth: 0,
        expYear: 0,
        cvc: ""
      }
  }

  _numberCardInputChanged(text){
    this.params.number = text;
  }
  _CVCInputChanged(text){
    this.params.cvc = text;
  }
  _monthInputChanged(text){
    this.params.expMonth = parseInt(text);
  }
  _yearInputChanged(text){
    this.params.expYear = parseInt(text);
  }

  _goToChargeSuccessFul(customerId){
    this.props.navigation.navigate("ChargeSuccessFul", {customer_id: customerId});
  }

  _sendToken = async()=>{
      const token = await stripe.createTokenWithCard(this.params);
      token_id = token.tokenId;
      try{
        const response = await fetch("http://192.168.0.17/fundraising/STRIPE/create_charge_customer.php",
        {
          method:'POST',
          headers: {
            'Accept': 'application/x-www-form-urlencoded', // application/json ne marche pas
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          body: `stripeToken=${token_id}`
        });
        const responseJson = await response.json();
        this._goToChargeSuccessFul(responseJson);
      }
      catch(err){
        alert(err);
      }

  }

  render() {
    return (
      <View>
        <TextInput
          placeholder="4242 4242 4242 4242"
          onChangeText={(text)=>this._numberCardInputChanged(text)}
        />
        <TextInput
          placeholder="123"
          onChangeText={(text)=>this._CVCInputChanged(text)}
        />
        <TextInput
          placeholder="01"
          onChangeText={(text)=>this._monthInputChanged(text)}
        />
        <TextInput
          placeholder="19"
          onChangeText={(text)=>this._yearInputChanged(text)}
        />
        <Button
          onPress={()=>this._sendToken()}
          title="Valider"
        />
      </View>
    )
  }
}

export default CardForm;

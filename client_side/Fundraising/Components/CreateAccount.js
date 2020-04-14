import React from 'react';
import { StyleSheet, Button } from 'react-native';



class CreateAccount extends React.Component {

  _createAccount = () => {
    fetch("http://192.168.0.17/fundraising/STRIPE/creat_acc_trt.php")
      .then((response)=>response.json())
      .then((responseJson)=>{
        alert(responseJson);
      })

  }

  render() {
    return(
      <Button
        onPress={()=> this._createAccount()}
        title="Learn More"
        color="#841584"
        accessibilityLabel="Learn more about this purple button"
      />
    )
  }
}

export default CreateAccount;

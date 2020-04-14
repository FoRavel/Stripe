import React from 'react';
import {View, Text, Button} from 'react-native';

class ChargeSuccessFul extends React.Component{
  constructor(props){
    super(props);
    this.state = {
      amount: 0
    }
  }

  render(){
    return(

      <View>
        <Text>Versement réussi!</Text>
        <Text>{this.props.navigation.getParam("customer_id")}</Text>
        <Button
          title="Terminer"
          onPress={()=>this.props.navigation.popToTop()}
        />
      </View>
    );
  }


}

export default ChargeSuccessFul;

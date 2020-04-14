import React from 'react';
import { StyleSheet, View, Text, Button, NativeModules } from 'react-native';
import ToastExample from "./ViewToastModule";

class ToastEx extends React.Component {
  constructor(props){
    super(props);
    this.state = {
      amount: 0
    }
  }

  _loadAmount = () => {
    fetch("http://192.168.0.17/fundraising/STRIPE/display_crowdfundingDetails.php")
      .then((response)=>response.json())
      .then((responseJson)=> {
        this.setState({amount: responseJson});
      })
  }

  _test(){
    return 10;
  }

  componentDidMount(){
    this._loadAmount();
  }


  render() {
    return(
      <View  style={styles.container}>
        <Text>Montant de la cagnotte</Text>
        <Text>{this.state.amount}</Text>
        <View style={styles.bottom}>
          <Button
            style={{ height: 50 }}
            onPress={() => ToastExample.navigateToExample()}
            title='Start example activity'
          />
        </View>
      </View>
    )
  }
}

const styles = StyleSheet.create({
  container:{
    flex: 1,
    alignItems: 'center'
  },
  bottom:{
    flex: 1
  }
})

export default ToastEx;

import React from 'react';
import { StyleSheet, View, Text, Button} from 'react-native';


class CrowdfundingView extends React.Component {
  constructor(props){
    super(props)
    this.state = {
      amount: 0
    }
  }

  _loadAmount = async() => {
    const response = await fetch("http://192.168.0.17/fundraising/STRIPE/display_crowdfundingDetails.php");
    const responseJson = await response.json();
    this.setState({amount: responseJson});
  }

  _goToInputAmount = () => {
    this.props.navigation.navigate("InputAmountForm");
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
            onPress={()=>this._goToInputAmount()}
            title='Participer'
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

export default CrowdfundingView;

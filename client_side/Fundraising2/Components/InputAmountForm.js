import React from 'react';
import{View,TextInput,Button} from 'react-native';

class InputAmountForm extends React.Component{
  constructor(props){
    super(props);
    this.input = "";
  }

  _inputChanged(text){
    this.input = text;
  }

  _goToCardForm(){
    this.props.navigation.navigate("CardForm");
  }

  render(){
    return(
      <View>
        <TextInput
          placeholder='Saisir un montant'
          onChangeText={()=>this._inputChanged()}
        />
        <Button
          title="Valider"
          onPress={()=>this._goToCardForm()}
        />
      </View>
    )
  }

}
export default InputAmountForm;

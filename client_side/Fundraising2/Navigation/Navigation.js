import React from 'react';
import{createStackNavigator, createAppContainer} from 'react-navigation';
import CardForm from '../Components/CardForm';
import CrowdfundingView from '../Components/CrowdfundingView';
import InputAmountForm from '../Components/InputAmountForm';
import ChargeSuccessFul from '../Components/ChargeSuccessFul';

const SearchStackNavigator = createStackNavigator({
  CrowdfundingView:{
    screen: CrowdfundingView,
    navigationOptions:{
      title: 'Cagnotte en cours'
    }
  },
  InputAmountForm:{
    screen: InputAmountForm,
    navigationOptions:{
      title: 'Participer'
    }
  },
  CardForm:{
    screen: CardForm
  },
  ChargeSuccessFul:{
    screen:  ChargeSuccessFul
  }
});

const App = createAppContainer(SearchStackNavigator);

export default App;

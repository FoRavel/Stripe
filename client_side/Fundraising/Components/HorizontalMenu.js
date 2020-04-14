import React from 'react';
import { StyleSheet, View, Text, FlatList } from 'react-native';

class HorizontalMenu extends React.Component {
  constructor(props){
    super(props);
    this.tabs = [{key:1, name:'CAGNOTTE'}, {key:2, name: 'CHECKLIST'}, {key:3, name:'CHATROOM'}, {key:4, name:'GALERIE'}]
    this.state = {

    }
  }



  render() {
    return(
      
      <FlatList
        horizontal={true}
        data={this.tabs}
        renderItem={({item})=><Text style={styles.marginItem}>{item.name}</Text>}
        style={styles.margin}
      />

    )
  }
}

const styles = StyleSheet.create({
  margin: {
    margin: 13
  },
  marginItem:{
    marginLeft: 11,
    marginRight: 11,
    fontSize: 14
  }
})

export default HorizontalMenu;

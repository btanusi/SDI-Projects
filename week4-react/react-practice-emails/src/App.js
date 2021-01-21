import './App.css';
import React from 'react'
import ShowSelected from './ShowSelected'
import CreateEmail from './CreateEmail'

class App extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      allEmails: [],
      selectedEmail: {},
      createEmailFlag: false,
      searchText: '',
    }
  }

  async componentDidMount() {
    const response = await fetch('http://localhost:3001/emails')
    const json = await response.json()
    this.setState({allEmails: json})
  }

  emailSent(){
    this.setState({createEmailFlag: false})
  }

  onSearchTextChange(event){
    this.setState({searchText: event.target.value})
  }

  onSearch(){
    alert(JSON.stringify((this.state.allEmails.filter(email => email.subject === this.state.searchText))))
    this.setState({
      selectedEmail: this.state.allEmails.filter(email => email.subject === this.state.searchText)
    })
  }
  

  render() {
    return (
    <div>
      <h1>Inbox</h1>
      <button type="button" 
              onClick={() => this.setState({createEmailFlag: true})}>
        Create Email
      </button>
      <input type="text" onChange={event => this.setState({searchText: event.target.value})}/>
      <button type="button" 
              onClick={this.onSearch.bind(this)}>
        Search Emails
      </button>
      <table border="5">
        {
          this.state.allEmails.map(
            email =>
              <tr>
                <td> {email.subject} </td>
                <td> {email.sender} </td>
                <td>
                  <button type="button" onClick={ () => 
                    this.setState({selectedEmail: email})
                  }>Show</button>
                </td>
              </tr>
          )
        }
      </table>
      <CreateEmail
        createEmailFlag = {this.state.createEmailFlag}
        emailSent = {this.emailSent.bind(this)}
      />
      <ShowSelected
        email = {this.state.selectedEmail}
      />

    </div>
    )
  }
}

export default App;

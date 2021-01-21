import React from 'react';

class CreateEmail extends React.Component {
    constructor(props) {
        super(props)
    }

    createEmailObj() {
        var email = {}
        email.sender = document.getElementById('sender').value
        email.recipient = document.getElementById('recipient').value
        email.subject = document.getElementById('subject').value
        email.message = document.getElementById('message').value
        email.date = document.getElementById('date').value
        this.sendEmail(email)
        this.props.emailSent()
    }

    async sendEmail(email) {
        await fetch('http://localhost:3001/send',{
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify(email)
        })
    }
    render() {
        if (this.props.createEmailFlag) {
            return (
                <div>
                    <label for="sender">Sender</label>
                    <input type="text" id="sender"/> <br/>
                    <label for="recipient">Recipient</label>
                    <input type="text" id="recipient"/> <br/>
                    <label for="subject">Subject</label>
                    <input type="text" id="subject"/> <br/>
                    <label for="message">Message</label>
                    <textarea id="message"></textarea> <br/>
                    <label for="date">Date</label>
                    <input type="text" id="date"/> <br/>
                    <button type="submit" onClick={this.createEmailObj.bind(this)}>Send</button>
                </div>
            )
        } else {
            return null
        }
    }
}

export default CreateEmail
function ShowSelected(props){
    console.log(props.email.sender)
    if(Object.entries(props.email).length !== 0){
        return(
            <table>
            <tr>
              <th>Sender:</th>
              <td>{props.email.sender}</td>
            </tr>
            
              <th>Recipient:</th>
              <td>{props.email.recipient}</td>
            
            <tr>
              <th>Subject:</th>
              <td>{props.email.subject}</td>
            </tr>
            <tr>
              <th>Message:</th>
              <td>{props.email.message}</td>
            </tr>
            <tr>
              <th>Date:</th>
              <td>{props.email.date}</td>
            </tr>
          </table>
        )
    } else {
        return null
    }
}
export default ShowSelected
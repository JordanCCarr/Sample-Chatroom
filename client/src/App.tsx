import React, { useState } from 'react';
import './App.css';
import { SidePanel } from './app/SidePanel/SidePanel';
import { MessageBoard } from './app/MessageBoard/MessageBoard';

function App() {
  const [selectedChatroom, setChatroom] =  useState('');
  const [activeUser, setActiveUser] = useState('');
  const [enteredUser, setEnteredUser] = useState('');

  return (
      <div className="App">
        { activeUser === '' ? 
          <div className="log-in">
            Please Log In
            <input type="string" onChange={(event) => setEnteredUser(event.target.value)} value={enteredUser}/>
            <div className="log-in-button" onClick={() => { 
                  const inner = async () => {
                      return await fetch('http://localhost:8080/users/auth', {
                          method: 'POST',
                          body: enteredUser,
                      });
                  };
                  inner().then(response => response.json()).then(data => {
                    if (data === true) {
                      setActiveUser(enteredUser);
                    } else {
                      setEnteredUser('');
                    }
                  });
              }}>
              Submit
            </div>
            <div className="log-in-button" onClick={() => { 
                const inner = async () => {
                    return await fetch('http://localhost:8080/users', {
                        method: 'POST',
                        body: enteredUser,
                    });
                };
                inner();
                setEnteredUser('');
            }}>
              Create Account
            </div>
          </div> 
        :
          <>
            <div className="side-panel-container">
              <SidePanel listingOnClick={(chatroom: string) => setChatroom(chatroom)}/>
            </div>
            <div className="message-board-container">
              <MessageBoard
                selectedChatroom={selectedChatroom}
                logOutAction={() => {
                  setActiveUser('');
                  setEnteredUser('');
                }}
                activeUser={activeUser}/>
            </div>
          </>
        }
      </div>
  );
}

export default App;

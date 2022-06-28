import React, { useEffect, useState } from 'react';
import './MessageBoard.css';

interface MessageBoardProps {
    selectedChatroom: string;
    logOutAction: () => void;
    activeUser: string;
};

interface Message {
    messageContent: string;
    username: string;
    timestamp: Date;
}

const createMessageDisplay = (message: string, username: string) => {
    return (
        <div className="message">
            {username}
            <div className="message-bubble">
                {message}
            </div>
        </div>
    )
};

/**
 * The right hand panel containg the list of messages for a chatroom
 */
export const MessageBoard = ({selectedChatroom, logOutAction, activeUser}: MessageBoardProps) => {
    const [messages, setMessages] = useState([] as Message[]);
    const [newMessage, setNewMessage] = useState('');
    useEffect(() => {
        if (selectedChatroom !== '') {
            fetch(`http://localhost:8080/messages/${selectedChatroom}`, {
                method: 'GET',
        }).then(response => response.json())
        .then(data => setMessages(data));
        }
    }, [selectedChatroom]);
    return (
        <div className="messageboard">
            <div className="message-header">
                {selectedChatroom !== '' ? selectedChatroom : 'Select a chatroom'}
                <div className='message-button' onClick={logOutAction}>
                    Log Out
                </div>
            </div>
            <div className="message-body">
                {messages.map(message => createMessageDisplay(
                    message.messageContent,
                    message.username
                ))}
            </div>
            <div className="message-footer">
                <input type="string" onChange={(event) => setNewMessage(event.target.value)} value={newMessage}/>
                <div className="message-button" onClick={() => { 
                    const inner = async () => {
                       await fetch(`http://localhost:8080/messages/${selectedChatroom}`, {
                            method: 'POST',
                            headers: {
                                'Accept': 'application/json',
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify({
                                messageContent: newMessage,
                                username: activeUser
                            }),
                        });
                    };
                    inner();
                    setNewMessage('');
                }}>
                    Post
                </div>
            </div>
        </div>
    );
};
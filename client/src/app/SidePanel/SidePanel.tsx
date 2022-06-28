import React, { useEffect, useState } from 'react';
import './SidePanel.css';

const createChatroomListing = (chatroom: string, onClick: (chatroom: string) => void) => {
    return (
        <div className="side-panel-listing" onClick={() => onClick(chatroom)}>
            {chatroom}
        </div>
    );
};

interface SidePanelProps {
    listingOnClick: ((chatroom: string) => void);
}

/**
 * The left hand side panel containing the list of chatroom servers
 */
export const SidePanel = ({listingOnClick: onClick}: SidePanelProps) => {
    const [chatrooms, setChatrooms] = useState([] as string[]);
    const [newChat, setNewChat] = useState('');
    useEffect(() => {
        fetch('http://localhost:8080/chatrooms', {
                method: 'GET',
        }).then(response => response.json())
        .then(data => setChatrooms(data));
    }, []); 
    return (
        <div className="side-panel">
            Chatrooms
            <div className="chatroom-display">
                {chatrooms.map(chatroom => createChatroomListing(chatroom, onClick))}
            </div>
            <div className="side-panel-footer">
                <input type="string" onChange={(event) => setNewChat(event.target.value)}/>
                <div className="new-chatroom-button" onClick={() => { 
                    const inner = async () => {
                       await fetch('http://localhost:8080/chatrooms', {
                            method: 'POST',
                            body: newChat,
                        });
                    };
                    inner();
                    setNewChat('');
                }}>
                    Create New Chatroom
                </div>
            </div>
        </div>
    );
};
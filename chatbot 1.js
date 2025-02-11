import React, { useState, useEffect, useRef } from "react";
import axios from "axios";
import "./chatbot.css";
import UserMenu from "./userMenu";

const Chatbot = () => {
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState("");
  const [showOptions, setShowOptions] = useState(false);
  const [showFaqs, setShowFaqs] = useState(false);
  const [showMoreHelp, setShowMoreHelp] = useState(false);
  const [showTicketForm, setShowTicketForm] = useState(false);
  const [ticket, setTicket] = useState({
    ticketDescription: ""
  });

  const [accountId] = useState(() => {
    return parseInt(localStorage.getItem("UserId"));
  });

  const messagesEndRef = useRef(null);

 const faqList = [
    { question: "How to reset my password?", answer: "Go to settings and click on 'Reset Password'." },
    { question: "How do I contact support?", answer: "You can contact support via email at support@telexa.com." },
    { question: "Where can I find my invoices?", answer: "Invoices are available in the billing section of your account." },
    { question: "What is 5G?", answer: "5G is the fifth generation of mobile telecommunications technology, offering higher speeds and more reliable connections than previous generations." },
    { question: "How can I check my balance?", answer: "To check your balance, dial *123# or use the official mobile app." },
   // { question: "What is the difference between 3G and 4G?", answer: "3G offers speeds up to 2 Mbps, while 4G can offer speeds up to 100 Mbps, providing faster internet access and smoother video calls." },
    { question: "How can I activate a data plan?", answer: "To activate a data plan, dial *111# and select the appropriate data plan option, or visit the nearest store." },
    { question: "What is roaming?", answer: "Roaming allows you to use your mobile phone to make calls and use data when traveling outside your home network coverage area." },
    { question: "How can I recharge my account?", answer: "To recharge your account, you can use online platforms like mobile banking apps, third-party recharge apps, or visit a retail shop." },
    { question: "What should I do if I lose my phone?", answer: "If you lose your phone, immediately contact customer support to block your SIM card and prevent unauthorized usage." },
    { question: "What is mobile hotspot?", answer: "A mobile hotspot allows you to share your phone's mobile data with other devices like laptops or tablets by converting it into a Wi-Fi network." }
  ];

  useEffect(() => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages]);

  const sendMessage = async (text) => {
    let newMessages = [...messages, { text, sender: "user" }];
    setMessages(newMessages);

    let response = "Sorry, I don't understand. Send 'Hi' to start a chat.";

    if (text.toLowerCase() === "hi") {
      response = "Hi, how may I help you?";
      setShowOptions(true);
      setShowFaqs(false);
      setShowMoreHelp(false);
      setShowTicketForm(false);
    } else if (text === "FAQs") {
      setShowFaqs(true);
      setShowOptions(false);
      setShowMoreHelp(false);
      response = "Here are our frequently asked questions:";
    } else if (text === "Raise Ticket") {
      setShowTicketForm(true);
      setShowOptions(false);
      setShowMoreHelp(false);
      return;
    } else if (text === "Check My Ticket Status") {
      if (!accountId) {
        response = "Customer ID not found. Please log in again.";
      } else {
        try {
          const res = await axios.get(`http://localhost:1121/chatbot/ticketstatus/${accountId}`);
          response = res.data;
          setShowMoreHelp(true);
          setShowOptions(false);
        } catch (error) {
          response = "Error fetching ticket status.";
        }
      }
    }
     else if (text.toLowerCase() === "no") {
      response = "Thank you! Reply 'Hi' to start a conversation.";
      setShowMoreHelp(false);
      setShowOptions(false);
    } else if (text.toLowerCase() === "yes") {
      response = "How may I assist you further?";
      setShowOptions(true);
      setShowMoreHelp(false);
    }

    setMessages([...newMessages, { text: response, sender: "bot" }]);
  };

  const handleTicketChange = (e) => {
    const { name, value } = e.target;
    setTicket({ ...ticket, [name]: value });
  };

  const submitTicket = async () => {
    if (!ticket.ticketDescription) {
      alert("Please describe your issue.");
      return;
    }

    const currentDate = new Date();
    const formattedDate = currentDate.toISOString().slice(0, 19).replace("T", " ");

    const newTicket = {
      created_at: formattedDate,
      employ_id: 1,
      issue: ticket.ticketDescription,
      status: "Open",
      updated_at: null,
      user_id: accountId
    };

    try {
      await axios.post("http://localhost:1121/supportTickets/addSupportTickets", newTicket, {
        headers: { "Content-Type": "application/json" }
      });
      setMessages([...messages, { text: "Your ticket has been raised successfully!", sender: "bot" }]);
      setShowTicketForm(false);
      setTicket({ ticketDescription: "" });
      setShowMoreHelp(true);
    } catch (error) {
      alert(`Error: ${error.response?.data?.message || "Failed to submit ticket."}`);
      setShowTicketForm(false);
      setTicket({ ticketDescription: "" });
      setShowMoreHelp(true);
    }
  };

  // Cancel function to close the ticket form and return to the main menu
  const cancelTicket = () => {
    setShowTicketForm(false);
    setShowOptions(true);
    setTicket({ ticketDescription: "" });
  };

  return (
    <div className="main-container">
      <UserMenu />
      <div className="chatbot-container">
        <div className="chatbot-header">Telexa</div>
        <div className="chatbot-messages">
          {messages.map((msg, index) => (
            <div key={index} className={msg.sender}>{msg.text}</div>
          ))}
          <div ref={messagesEndRef} />

          {showTicketForm && (
            <div className="chatbot-ticket-form">
              <h4>Raise a Ticket</h4>
              <textarea
                name="ticketDescription"
                placeholder="Describe your issue"
                value={ticket.ticketDescription}
                onChange={handleTicketChange}
                required
              />
              <div className="ticket-buttons">
                <button onClick={submitTicket}>Submit</button>
                <button onClick={cancelTicket} className="cancel-btn">Cancel</button>
              </div>
            </div>
          )}
        </div>

        {showFaqs && (
          <div className="chatbot-options">
            {faqList.map((faq, index) => (
              <button key={index} onClick={() => {
                setMessages([
                  ...messages, 
                  { text: faq.question, sender: "user" },
                  { text: faq.answer, sender: "bot" }
                ]);
                setShowFaqs(false);
                setShowMoreHelp(true);
              }}>{faq.question}</button>
            ))}
          </div>
        )}

        {showMoreHelp && (
          <div className="chatbot-options">
            <p>Do you need more assistance?</p>
            <button onClick={() => sendMessage("Yes")}>Yes</button>
            <button onClick={() => sendMessage("No")}>No</button>
          </div>
        )}

        {showOptions && !showTicketForm && (
          <div className="chatbot-options">
            <button onClick={() => sendMessage("Raise Ticket")}>Raise Ticket</button>
            <button onClick={() => sendMessage("Check My Ticket Status")}>Check My Ticket Status</button>
            <button onClick={() => sendMessage("FAQs")}>FAQs</button>
          </div>
        )}

        <div className="chatbot-input">
          <input
            type="text"
            value={input}
            onChange={(e) => setInput(e.target.value)}
            placeholder="Type Hi to start conversation..."
          />
          <button onClick={() => sendMessage(input)}>Send</button>
        </div>
      </div>
    </div>
  );
};

export default Chatbot;

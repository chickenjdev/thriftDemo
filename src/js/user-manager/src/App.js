import React, {useState} from 'react';
import './App.css';
import axios from "axios";
import UserForm from "./UserForm";
function App() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [resultLogin, setResultLogin] = useState();
    const [resultSession, setResultSession] = useState();

    const [state, setState] = useState("login");

    function handleUser(user) {
        console.log(user)
        setUsername(user)
    }

    function handlePass(pass) {
        setPassword(pass)
    }

    const summitLogin = e => {
        const loginJson = {
            username: username,
            password: password
        }
        console.log('username ' + loginJson.username)
        const headers = {
            'Content-Type': 'application/json',
            "Access-Control-Allow-Origin": "*"
        }
        axios.post(`http://localhost:8089/login`, {loginJson}, {headers: headers})
            .then(res => {
                setResultLogin(res.data.userInfo);
                setResultSession(res.data.sessionInfo);
                if (res.data.error === 0) {
                    console.log("login success", res.data.sessionInfo.sessionId);
                    setState("userInfo");
                }
            })
    }

    function FormHeader() {
        return (
            <h2 id="headerTitle">Login user</h2>
        )
    };

    function Form() {
        return (
            <div>
                <FormInputUser name="userInput"
                               description="Username" placeholder="Username" type="text"/>
                <FormInputPass name="passInput"
                               description="Password" placeholder="Password" type="password"/>
                <FormButton title="Log in"/>
                <div className="row"></div>
            </div>
        )
    }

    function FormInputUser({name, description, placeholder, type}) {
        return (
            <div className="row">
                <label>{description}</label>
                <input key={name} value={username}
                       type={type} placeholder={placeholder} onChange={e => {setUsername(e.target.value)}}
                />
            </div>
        )
    }

    function FormInputPass({name, description, placeholder, type}) {
        return (
            <div className="row">
                <label>{description}</label>
                <input key={name} value={password}
                       type={type} placeholder={placeholder} onChange={e => handlePass(e.target.value)}
                />
            </div>
        )
    }

    function FormButton({doSomeThing, title}) {
        return (
            <div id="button" className="row loginbutton">
                <button onClick={summitLogin}>{title}</button>
            </div>
        )
    }

    return (
        <>
            <div>
                { state === "login" ?
                    <div id="loginform">
                        <FormHeader></FormHeader>
                        <Form/>
                    </div>
                    :
                    <div id="loginform">
                        <UserForm userInfo={resultLogin} sessionIn = {resultSession}></UserForm>
                    </div>
                }
            </div>

        </>
    );
}

export default App;

import React, {useState} from 'react';
import './App.css';
import axios from "axios";

function UserForm({userInfo, sessionIn}) {

    const [info, setInfo] = useState(userInfo)
    const [session, setSession] = useState(sessionIn)
    const [resultUpdate, setResultUpdate] = useState();

    function handleChange(evt) {
        console.log(evt.target.value)
        setInfo({
            ...info,
            [evt.target.name]: evt.target.value
        });
    }

    const summitUpdate = e => {
        const loginJson = {
            username: info.username,
            name: info.name,
            address: info.address,
            age: info.age,
            sessionId : session.sessionId
        }
        console.log('username ' + loginJson)
        const headers = {
            'Content-Type': 'application/json',
            "Access-Control-Allow-Origin": "*"
        }
        axios.post(`http://localhost:8089/update`, {loginJson}, {headers: headers})
            .then(res => {
                if (res.data.error === 0){
                    setResultUpdate("update success");
                }
                setResultUpdate("update success");
            })
    }

    function FormHeader() {
        return (
            <h2 id="headerTitle">User Info</h2>
        )
    };

    function Form() {
        return (
            <div>
                <FormInputUserInfo name="username" value={info.username} description = "username" type="text"/>
                <FormInputUserInfo name="name" value={info.name} description = "name" type="text"/>
                <FormInputUserInfo name="address" value={info.address} description = "address" type="text"/>
                <FormInputUserInfo name="age" value={info.age} description = "age" type="text"/>
                <FormButton title="Update"/>
                <div><p>{resultUpdate}</p></div>
                <div className="row"></div>
            </div>
        )
    }

    function FormInputUserInfo({name, value, description, placeholder, type}) {
        return (
            <div className="row">
                <label>{description}</label>
                <input key={name} value={value}
                       type={type} placeholder={placeholder} onChange={e => handleChange(e)}
                />
            </div>
        )
    }


    function FormButton({doSomeThing, title}) {
        return (
            <div id="button" className="row loginbutton">
                <button onClick={summitUpdate}>{title}</button>
            </div>
        )
    }

    return (
        <>
            <div id="userform">
                <FormHeader></FormHeader>
                <Form/>
            </div>
        </>
    );
}

export default UserForm;

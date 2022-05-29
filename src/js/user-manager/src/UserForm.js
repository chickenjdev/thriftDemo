import React, {useState} from 'react';
import './App.css';
import axios from "axios";

function UserForm({userInfo}) {
    const [userInfoUpdate, setUserInfoUpdate] = useState({});

    const [state, setState] = React.useState({})

    function handleChange(evt) {
        const value = evt.target.value;
        setState({
            ...state,
            [evt.target.name]: value
        });
    }

    function FormHeader() {
        return (
            <h2 id="headerTitle">User Info</h2>
        )
    };

    function Form({userInfo}) {
        return (
            <div>
                <FormInputUserInfo name="username" value={userInfo.username} description = "username" type="text"/>
                <FormInputUserInfo name="name" value={userInfo.name} description = "name" type="text"/>
                <FormInputUserInfo name="address" value={userInfo.address} description = "address" type="text"/>
                <FormInputUserInfo name="age" value={userInfo.age} description = "age" type="text"/>
                <FormButton title="Update"/>
                <div className="row"></div>
            </div>
        )
    }

    function FormInputUserInfo({name, value, description, placeholder, type}) {
        return (
            <div className="row">
                <label>{description}</label>
                <input key={name} value={value}
                       type={type} placeholder={placeholder} onChange={handleChange}
                />
            </div>
        )
    }


    function FormButton({doSomeThing, title}) {
        return (
            <div id="button" className="row loginbutton">
                <button>{title}</button>
            </div>
        )
    }

    return (
        <>
            <div id="userform">
                <FormHeader></FormHeader>
                <Form userInfo={userInfo}/>
            </div>
        </>
    );
}

export default UserForm;

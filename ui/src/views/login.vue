<template>
  <section class="main">
    <div class="bottom-grid">
      <div class="logo">
        <h1><a href="index.html"> Welcome to Betta</a></h1>
      </div>
    </div>
    <div class="content-w3ls">
      <div class="text-center icon">
        <img :src="logo" />
      </div>

      <div class="content-bottom">
        <form action="#" method="post" @submit.prevent="handleLogin">
          <div class="field-group">
            <span class="fa fa-user" aria-hidden="true"></span>
            <div class="wthree-field">
              <input
                v-model="loginForm.username"
                type="text"
                placeholder="Username"
                required
              />
            </div>
          </div>
          <div class="field-group">
            <span class="fa fa-lock" aria-hidden="true"></span>
            <div class="wthree-field">
              <input
                v-model="loginForm.password"
                type="Password"
                placeholder="Password"
                required
              />
            </div>
          </div>
          <div class="wthree-field">
            <button type="submit" class="btn">Sign in</button>
          </div>
          <ul class="list-login">
            <li class="switch-agileits">
              <label class="switch">
                <input type="checkbox" v-model="loginForm.rememberMe" />
                <span class="slider round"></span>
                记 住 密 码
              </label>
            </li>
            <li>
              <!-- <a href="#" class="text-right">forgot password?</a> -->
            </li>
            <li class="clearfix"></li>
          </ul>
          <ul class="list-login-bottom">
            <li class="">
              <!-- <a href="#url" class="">Create Account</a> -->
            </li>
            <li class="">
              <!-- <a href="#url" class="text-right">Need Help?</a> -->
            </li>
            <li class="clearfix"></li>
          </ul>
        </form>
      </div>
    </div>
    <div class="copyright">
      <!-- <p>
        © 2020 Meetup signin form. All rights reserved | Design by
        <a href="http://w3layouts.com">W3layouts</a>
      </p> -->
    </div>
  </section>
</template>

<script>
import Cookies from "js-cookie";
import { encrypt, decrypt } from "@/utils/jsencrypt";
import logoImg from "@/assets/logo/logo-white.svg";

export default {
  name: "Login",
  data() {
    return {
      logo: logoImg,
      loginForm: {
        // username: "admin",
        // password: "admin123",
        username: "",
        password: "",
        rememberMe: false,
        code: "",
        uuid: "",
      },
      loginRules: {
        username: [
          { required: true, trigger: "blur", message: "请输入您的账号" },
        ],
        password: [
          { required: true, trigger: "blur", message: "请输入您的密码" },
        ],
        code: [{ required: true, trigger: "change", message: "请输入验证码" }],
      },
      loading: false,
      // 验证码开关
      captchaEnabled: true,
      // 注册开关
      register: false,
      redirect: undefined,
    };
  },
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true,
    },
  },
  created() {
    this.getCookie();
  },
  methods: {
    getCookie() {
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      const rememberMe = Cookies.get("rememberMe");
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password:
          password === undefined ? this.loginForm.password : decrypt(password),
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe),
      };
    },
    handleLogin() {
      this.loading = true;
      if (this.loginForm.rememberMe) {
        Cookies.set("username", this.loginForm.username, { expires: 30 });
        Cookies.set("password", encrypt(this.loginForm.password), {
          expires: 30,
        });
        Cookies.set("rememberMe", this.loginForm.rememberMe, { expires: 30 });
      } else {
        Cookies.remove("username");
        Cookies.remove("password");
        Cookies.remove("rememberMe");
      }
      this.$store
        .dispatch("Login", this.loginForm)
        .then(() => {
          this.$router.push({ path: this.redirect || "/" }).catch(() => {});
        })
        .catch(() => {
          this.loading = false;
        });
    },
  },
};
</script>

<style>
html {
  scroll-behavior: smooth;
}

body,
html {
  margin: 0;
  padding: 0;
  color: #585858;
}

* {
  box-sizing: border-box;
  font-family: "Mukta", sans-serif;
}

/*  wrapper */
.wrapper {
  width: 100%;
  padding-right: 15px;
  padding-left: 15px;
  margin-right: auto;
  margin-left: auto;
}

@media (min-width: 576px) {
  .wrapper {
    max-width: 540px;
  }
}

@media (min-width: 768px) {
  .wrapper {
    max-width: 720px;
  }
}

@media (min-width: 992px) {
  .wrapper {
    max-width: 960px;
  }
}

@media (min-width: 1200px) {
  .wrapper {
    max-width: 1140px;
  }
}

/*  /wrapper */

.d-grid {
  display: grid;
}

button,
input,
select {
  -webkit-appearance: none;
  outline: none;
}

button,
.btn,
select {
  cursor: pointer;
}

a {
  text-decoration: none;
}

h1,
h2,
h3,
h4,
h5,
h6,
p,
ul,
ol {
  margin: 0;
  padding: 0;
}

body {
  background: #f1f1f1;
  margin: 0;
  padding: 0;
}

form,
fieldset {
  border: 0;
  padding: 0;
  margin: 0;
}

body a:hover {
  text-decoration: none;
}

.clearfix {
  clear: both;
}

/* content */

/*
  Responsive form elements
  Flexbox layout
*/

/*/////////////// GLOBAL STYLES ////////////////////*/

body {
  font-family: "Mukta", sans-serif;
}
.main {
  background: url(../assets/images/bg.jpg) no-repeat center;
  background-size: cover;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  -ms-background-size: cover;
  min-height: 100vh;
  padding: 2em 0;
  position: relative;
  z-index: 1;
  justify-content: center;
  display: grid;
  grid-template-rows: 1fr auto 1fr;
  align-items: center;
}
.main:before {
  position: absolute;
  content: "";
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.6);
  z-index: -1;
}
.text-center {
  text-align: center;
}

/*/////////////// FONT STYLES ////////////////////*/

.content-w3ls {
  margin: 2em auto;
  padding: 2em 4em;
}
ul.links-unordered-list li.active a {
  background: rgba(255, 255, 255, 0.05);
  padding: 5px 20px;
  color: #fff;
}
.icon img {
  width: 50px;
}
.icon {
  margin-bottom: 3em;
}
.content-bottom {
  padding: 0em;
  margin: 0em;
  position: relative;
  z-index: 1;
}
.logo h1 a {
  font-size: 35px;
  color: #fff;
  text-transform: capitalize;
  font-weight: 600;
  letter-spacing: 1px;
}
.content-w3ls ul li {
  display: inline-block;
}

li.switch-agileits {
  float: left;
}

ul.list-login li:nth-child(2) {
  float: right;
}
ul.list-login-bottom li:nth-child(2) {
  float: right;
}

.field-group label {
  font-size: 15px;
}

.check {
  margin: 0.5em 0;
}

.checkbox input,
.radio input {
  position: absolute;
  left: -9999px;
}

.checkbox i {
  position: absolute;
  bottom: 5px;
  left: 2px;
  display: block;
  width: 14px;
  height: 14px;
  outline: none;
  border: none;
  background: #fff;
}

.check label {
  margin: 0;
  font-size: 1em;
  text-transform: capitalize;
  color: #fff;
  letter-spacing: 1px;
  font-weight: 300;
}

.checkbox {
  position: relative;
  padding-left: 28px !important;
  cursor: pointer;
}

.checkbox input:checked + i:after,
.radio input:checked + i:after {
  opacity: 1;
}

.checkbox input + i:after,
.radio input + i:after {
  position: absolute;
  opacity: 0;
  transition: opacity 0.1s;
  -o-transition: opacity 0.1s;
  -ms-transition: opacity 0.1s;
  -moz-transition: opacity 0.1s;
  -webkit-transition: opacity 0.1s;
}

/*/////////////// FORM STYLES ////////////////////*/

form .field-group {
  background: #fff;
  margin-bottom: 20px;
  padding: 0px 10px 0px 0;
  border-radius: 35px;
  -webkit-border-radius: 35px;
  -moz-border-radius: 35px;
  -ms-border-radius: 35px;
  -o-border-radius: 35px;
  position: relative;
}

form .field-group span {
  color: #999;
  position: absolute;
  left: 25px;
  top: 18px;
  opacity: 0.5;
}

ul.list-login {
  margin-top: 1.5em;
  margin-bottom: 1em;
}

.wthree-field button.btn {
  background: #fc636b;
  background: #a3b955;
  border: none;
  color: #fff;
  padding: 12px 15px;
  text-transform: uppercase;
  font-family: "Mukta", sans-serif;
  font-size: 16px;
  width: 100%;
  letter-spacing: 2px;
  outline: none;
  cursor: pointer;
  transition: 0.5s all;
  -webkit-transition: 0.5s all;
  -moz-transition: 0.5s all;
  -o-transition: 0.5s all;
  -ms-transition: 0.5s all;
  border-radius: 35px;
  -webkit-border-radius: 35px;
  -moz-border-radius: 35px;
  -ms-border-radius: 35px;
  -o-border-radius: 35px;
}
.wthree-field button.btn:hover {
  background: #97ab4f;
}

.bottom-grid,
.bottom-grid1 {
  text-align: center;
}
ul.links-unordered-list li {
  display: inline-block;
  margin-right: 10px;
}
ul.links-unordered-list li a {
  font-size: 16px;
  color: #ccc;
  font-weight: normal;
  letter-spacing: 1px;
  padding: 0 5px;
  text-transform: capitalize;
}
.wthree-field input {
  padding: 12px 0 12px 50px;
  font-size: 18px;
  color: #999;
  letter-spacing: 0.5px;
  border: none;
  background: transparent;
  box-sizing: border-box;
  font-family: "Mukta", sans-serif;
  width: 100%;
  outline: none;
}

::-webkit-input-placeholder {
  /* Edge */
  color: #999;
}

:-ms-input-placeholder {
  /* Internet Explorer 10-11 */
  color: #999;
}

::placeholder {
  color: #999;
}

/* switch */

label.switch {
  position: relative;
  display: inline-block;
  height: 18px;
  padding-left: 3em;
  cursor: pointer;
  color: #ccc;
  font-weight: 300;
}

li:nth-child(2) a,
label.switch {
  font-size: 15px;
  letter-spacing: 0.5px;
  font-weight: 400;
  color: #ccc;
  text-transform: capitalize;
}

li:nth-child(2) a:hover,
label.switch:hover {
  color: #fff;
}
ul.list-login-bottom li:nth-child(1) a,
ul.list-login-bottom li:nth-child(2) a {
  font-size: 15px;
  letter-spacing: 2px;
  font-weight: normal;
  text-transform: uppercase;
  color: #fff;
}
ul.list-login-bottom li:nth-child(1):hover a,
ul.list-login-bottom li:nth-child(2) a:hover {
  color: #ccc;
}

.switch input {
  display: none;
}

.slider {
  position: absolute;
  cursor: pointer;
  top: 2px;
  left: 0;
  right: 0;
  bottom: 0;
  width: 33px;
  background-color: rgba(255, 255, 255, 0.15);
  -webkit-transition: 0.4s;
  transition: 0.4s;
}

.slider:before {
  position: absolute;
  content: "";
  height: 10px;
  width: 10px;
  left: 4px;
  bottom: 3px;
  background-color: white;
  -webkit-transition: 0.4s;
  transition: 0.4s;
}

input:checked + .slider {
  background-color: #a3b955;
}

input:focus + .slider {
  box-shadow: 0 0 1px #2196f3;
}

input:checked + .slider:before {
  -webkit-transform: translateX(14px);
  -ms-transform: translateX(14px);
  transform: translateX(14px);
}

/* Rounded sliders */

.slider.round {
  border-radius: 34px;
}

.slider.round:before {
  border-radius: 50%;
}
/* //switch */
.copyright {
  padding: 0 20px;
}
.copyright p {
  color: #ccc;
  font-size: 17px;
  line-height: 26px;
  text-transform: capitalize;
  text-align: center;
  letter-spacing: 1px;
}
.copyright p a {
  color: #fff;
}
.copyright p a:hover {
  color: #97ab4f;
}
/* -- Responsive code -- */
@media screen and (max-width: 1280px) {
}
@media screen and (max-width: 1080px) {
}
@media screen and (max-width: 900px) {
}
@media screen and (max-width: 800px) {
}
@media screen and (max-width: 768px) {
  .logo h1 a {
    font-size: 30px;
  }
}
@media screen and (max-width: 668px) {
  .icon {
    margin-bottom: 2em;
  }
  .icon span.fa {
    font-size: 40px;
  }
  ul.list-login-bottom li:nth-child(1) a,
  ul.list-login-bottom li:nth-child(2) a {
    font-size: 14px;
  }
}
@media screen and (max-width: 600px) {
}
@media screen and (max-width: 568px) {
}

@media screen and (max-width: 480px) {
}

@media screen and (max-width: 415px) {
  .logo {
    margin-bottom: 10px;
  }
  .content-w3ls {
    padding: 2em 2em;
  }
}

@media screen and (max-width: 384px) {
  .content-w3ls {
    padding: 1em 1em;
    margin: 1em auto;
  }
  form .field-group {
    margin-bottom: 15px;
  }
  ul.list-login {
    margin-bottom: 0.5em;
  }
  ul.list-login-bottom li:nth-child(1) a,
  ul.list-login-bottom li:nth-child(2) a {
    letter-spacing: 1px;
  }
  .copyright p {
    letter-spacing: 0.5px;
  }
}

@media screen and (max-width: 375px) {
}
@media screen and (max-width: 320px) {
}
</style>

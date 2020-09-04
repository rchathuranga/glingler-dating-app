importScripts('https://www.gstatic.com/firebasejs/7.6.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/7.6.0/firebase-messaging.js');
firebase.initializeApp({
    apiKey: 'AIzaSyCx3Hcbb9SGvMlrPp8M1uKgB8g1KShtmxI',
    authDomain: 'glingler-2a90b.firebaseapp.com',
    databaseURL: 'https://glingler-2a90b.firebaseio.com',
    projectId: 'glingler-2a90b',
    storageBucket: 'glingler-2a90b.appspot.com',
    messagingSenderId: '1016024450731',
    appId: '1:1016024450731:web:1d4f66391135534527d9ee',
    measurementId: 'G-TPG6LLKKY0'
  });
const messaging = firebase.messaging();

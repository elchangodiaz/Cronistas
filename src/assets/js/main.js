const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
const configMensaje = require('./configMensaje');
const app = express();

app.use(bodyParser.json());
app.use(cors())
app.post('/formulario', (req, res) => {
 configMensaje(req.body);
 res.status(200).send();
})
app.listen(3000, () => {
 console.log('Servidor corriendo')
});


/*const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
const exphbs = require('express-handlebars');
const nodemailer = require("nodemailer");

const app = express();
const path = require('path');
app.use(bodyParser.json());
app.use(cors())

app.post('/formulario', (req, res) => {})
app.listen(3000, () => {
console.log('Servidor corriendo');
});
*/

/*
//View engine setup
app.engine('handlebars', exphbs());
app.set('view engine', 'handlebars');

// static folder
app.use('/app/contact', express.static(path.join(__dirname + '/contact')));

// Body Parser setup
app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())

app.get('/', (req, res) => {
  res.render('/app/contact');
});

app.listen(3000, () => {
  console.log("Servidor en -> http://localhost:3000");
});

*/
/*
app.post("/send-email", (req, res) => {
    var transporter = nodemailer.createTransport({
        host: "smtp.ethereal.email",
        post: 587,
        secure: false,
        auth: {
            user: "frederik.herzog@ethereal.email",
            pass: "PNTFt8ykwHBWBYgJKS",
        },
    });

    var mailOptions = {
        from: "Remitente",
        to: "elchangodiaz1@gmail.com",
        subject: "Enviado desde nodemail",
        text: "Hola mundo"
    }

    transporter.sendMail(mailOptions, (error, info) => {
        if(error){
            res.status(500).send(error.message);
        } else {
            console.log("Email enviado");
            res.status(200).json(req.body);
        }
    })
});

app.listen(3000, () => {
    console.log("Servidor en -> http://localhost:3000");
});
*/
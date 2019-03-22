const {createSever} = require("http");

let server = createSever((req, resp) => {
  resp.writeHead(200, {"Content-Type": "text/html"});
  resp.write(`
    <h1>Hello World!</h1>
    <p>You asked for <code>${req.url}</code></p>
  `);
});
server.listen(8000);

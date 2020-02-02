def call(Map args) {
  def node = args.node
  def dc = args.dc
  def image = args.image
  def env = args.env
  
  def post = new URL("http://dynconfig.${dc}.tivo.com:50000/dynconfigServerStore").openConnection();
  def message = '{"type": "dynconfigServerStore","server": {"container":["${image}"],"environment": "${env}","name": "${node}"}}'
  post.setRequestMethod("POST")
  post.setDoOutput(true)
  post.setRequestProperty("Content-Type", "application/json")
  post.getOutputStream().write(message.getBytes("UTF-8"));
  def postRC = post.getResponseCode();

  if(postRC.equals(200)) {
      return true
  }
  else {
      return false
  }
  
}

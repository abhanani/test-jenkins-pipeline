def call() {

  def post = new URL("http://dynconfig.qea1.tivo.com:50000/dynconfigServerStore").openConnection();
  def message = '{"type": "dynconfigServerStore","server": {"container":["docker.tivo.com/abhanani/station-policy-service:latest"],"environment": "usqe3","name": "stationpolicyservice-usqe3-02.qea1.tivo.com"}}'
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

def call(node, dc, image, env, closure) {
  def post = new URL("http://dynconfig.$dc.tivo.com:50000/dynconfigServerStore").openConnection();
  def message = '{"type": "dynconfigServerStore","server": {"container":["$image"],"environment": "$env","name": "$node"}}'
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

def call(nodeName, String dc, String environment, String imageName) {

  def post = new URL("http://dynconfig.$dc.tivo.com:50000/dynconfigServerStore").openConnection();
  def message = '{"type": "dynconfigServerStore", "server": {"container": ["$imageName"],"environment": "$environment","name": "$nodeName"}}'
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

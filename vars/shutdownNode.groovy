def call() {
  def get = new URL("http://stationPolicyServic-usqe3-02.qea1.tivo.com:40146/shutdown").openConnection();
  get.setRequestMethod("GET")
  get.setDoOutput(true)
  def getRC = get.getResponseCode();
  if(getRC.equals(200)) {
      return true
  }
  else {
      return false
  }
}

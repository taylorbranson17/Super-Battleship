interface PlayerInterface {
    public Coord takeTurn();
    public ShotResult receiveShot(Coord shot);
    public void receiveShotResult(ShotResult result);
    public Boolean validateTurn();
    public String getName();
}

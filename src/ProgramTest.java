
import com.google.java.contract.PreconditionError;
import org.junit.Test;


public class ProgramTest {

    public ProgramTest() {
        try {
            BugzillaException.init();
        } catch (BugzillaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getErrorMsg());
        }
    }


    @Test(expected = PreconditionError.class)
    public void shouldFailIfGoesFromUnconfirmedToVerified() throws Exception {
        Bug bug = bug();
        bug.setState(Bug.State.VERIFIED);
    }

    @Test(expected = PreconditionError.class)
    public void shouldFailIfGoesFromVerifiedToConfirmed() throws Exception {
        Bug bug = bug();
        bug.setState(Bug.State.VERIFIED);
        bug.setState(Bug.State.CONFIRMED);
    }

    @Test
    public void shouldFailIfGoesFromConfirmedToResolved() throws Exception {
        Bug bug = bug();
        bug.setState(Bug.State.CONFIRMED);
        bug.setAsResolved(Bug.Resolution.FIXED,"123");;
    }

    @Test(expected = PreconditionError.class)
    public void shouldFailIfGoesFromConfirmedToVerified() throws Exception {
        Bug bug = bug();
        bug.setState(Bug.State.CONFIRMED);
        bug.setState(Bug.State.VERIFIED);
    }

    @Test(expected = PreconditionError.class)
    public void shouldFailIfGoesFromVerifiedToUnconfirmed() throws Exception {
        Bug bug = bug();
        bug.setAsResolved(Bug.Resolution.FIXED,"123");;
        bug.setState(Bug.State.VERIFIED);
        bug.setState(Bug.State.UNCONFIRMED);
    }

    @Test
    public void shouldGoFromUnconfirmedToConfirmed() throws Exception {
        Bug bug = bug();
        bug.setState(Bug.State.CONFIRMED);
    }

    @Test(expected = PreconditionError.class)
    public void shouldFailGoesFromUnconfirmedToResolved() throws Exception {
        Bug bug = bug();
        bug.setState(Bug.State.RESOLVED);
    }

    @Test
    public void shouldGoFromConfirmedToInProgress() throws Exception {
        Bug bug = bug();
        bug.setState(Bug.State.CONFIRMED);
        bug.setState(Bug.State.INPROGRESS);
    }

    @Test
    public void shouldGoFromInProgressToResolved() throws Exception {
        Bug bug = bug();
        bug.setState(Bug.State.CONFIRMED);
        bug.setState(Bug.State.INPROGRESS);
        bug.setAsResolved(Bug.Resolution.FIXED,"123");
    }

    @Test
    public void shouldGoFromResolvedToVerified() throws Exception {
        Bug bug = bug();
        bug.setAsResolved(Bug.Resolution.FIXED,"123");
        bug.setState(Bug.State.VERIFIED);
    }

    @Test
    public void shouldGoFromInProgressToConfirmed() throws Exception {
        Bug bug = bug();
        bug.setState(Bug.State.CONFIRMED);
        bug.setState(Bug.State.INPROGRESS);
    }

    @Test
    public void shouldGoFromResolvedToConfirmed() throws Exception {
        Bug bug = bug();
        bug.setAsResolved(Bug.Resolution.FIXED,"123");
        bug.setState(Bug.State.CONFIRMED);
    }

    @Test(expected = PreconditionError.class)
    public void shouldFailIfResolutionIsEmpty() throws Exception {
        Bug bug = bug();
        bug.setAsResolved(Bug.Resolution.FIXED,"123");
        bug.setAsResolved(Bug.Resolution.FIXED,null);
    }

    @Test(expected = PreconditionError.class)
    public void testRegisterNullName() {
        Bugzilla bz = null;

        try {
            //Disable file handling by passing false to the constructor
            bz = new Bugzilla(false);
        } catch (BugzillaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        try {
            bz.register(null, "abc", Bugzilla.MemberType.USER);
        } catch (BugzillaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getErrorMsg());
        }
    }

    @Test(expected = PreconditionError.class)
    public void testChangeStateUnconfirmedToInProgress() {

        Bug bug = null;

        try {
            bug = new Bug(0, "crash on OK press");
        } catch (BugzillaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {

            bug.setState(Bug.State.INPROGRESS);

        } catch (BugStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getErrorMsg());

        }
    }

    private Bug bug() throws BugzillaException {
        return new Bug(5, "testBug");
    }

}

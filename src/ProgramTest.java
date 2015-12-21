
import com.google.java.contract.InvariantError;
import com.google.java.contract.PreconditionError;
import org.junit.Test;


public class ProgramTest {

    public ProgramTest() {
        try {
            BugzillaException.init();
        } catch (BugzillaException e) {
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

    @Test(expected = PreconditionError.class)
    public void shouldFailIfGoesFromConfirmedToResolved() throws Exception {
        Bug bug = bug();
        bug.setState(Bug.State.CONFIRMED);
        bug.setAsResolved(Bug.Resolution.FIXED, "123");
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
        bug.setAsResolved(Bug.Resolution.FIXED, "123");
        ;
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
        bug.setAsResolved(Bug.Resolution.FIXED, "123");
    }

    @Test
    public void shouldGoFromResolvedToVerified() throws Exception {
        Bug bug = bug();
        bug.setAsResolved(Bug.Resolution.FIXED, "123");
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
        bug.setAsResolved(Bug.Resolution.FIXED, "123");
        bug.setState(Bug.State.CONFIRMED);
    }

    @Test(expected = PreconditionError.class)
    public void shouldFailIfResolutionIsEmpty() throws Exception {
        Bug bug = bug();
        bug.setAsResolved(Bug.Resolution.FIXED, "");
        bug.setAsResolved(Bug.Resolution.FIXED, null);
    }

    @Test(expected = PreconditionError.class)
    public void shouldFailIfResolutionIsUnresolved() throws Exception {
        Bug bug = bug();
        bug.setAsResolved(Bug.Resolution.UNRESOLVED, "12313");
    }

    @Test(expected = PreconditionError.class)
    public void shouldFailIfBugWithEmptyDescription() throws Exception {
        new Bug(12, "");
        new Bug(12, null);
    }

    @Test(expected = PreconditionError.class)
    public void shouldFailIfModifiedAfterVerified() throws Exception {
        Bug bug = bug();
        bug.setAsResolved(Bug.Resolution.FIXED, "123");
        bug.setState(Bug.State.VERIFIED);
        bug.setAsResolved(Bug.Resolution.FIXED, "q23234");
    }

    @Test(expected = PreconditionError.class)
    public void testRegisterNullName() throws BugzillaException {
        new Bugzilla(false).register(null, "abc", Bugzilla.MemberType.USER);
    }

    @Test(expected = PreconditionError.class)
    public void testChangeStateUnconfirmedToInProgress() throws BugzillaException {
        new Bug(0, "crash on OK press").setState(Bug.State.INPROGRESS);
    }

    private Bug bug() throws BugzillaException {
        return new Bug(5, "testBug");
    }

}

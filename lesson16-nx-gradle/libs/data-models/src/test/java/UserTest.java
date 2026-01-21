public class UserTest {
    @org.junit.Test
    public void testUserCreation() {
        User user = new User("1", "John Doe", "john@example.com");
        org.junit.Assert.assertEquals("1", user.getId());
        org.junit.Assert.assertEquals("John Doe", user.getName());
    }
}

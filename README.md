# hb-05-many-to-many
@Entity
@Table(name ="course")
public class Course {
	...
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, 
			CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="instructor_id")
	private Instructor instructor;
	
	...}
  
  

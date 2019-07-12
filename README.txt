one to one = user Credentials
many to one = account transaction
one to many = user vehicle
many to many = user account


====================================================
Simple entities--User Bank

BANK and ADDRESS is an example for Embedded Object(value type and not collection type)

NOTE- A mapping table will always be created in case of @ElementCollection

Element collection -- User Address  (user1 has address but user doesnot have address) : here we had a Address class with Embeddable annotation

BANK_POSITIONS and BANK_CONTACTS are tables created through @ElementCollection for List and Map type respectively.
These are also Embeddable type but their classes doesnot exists.They are created on runtime in BANK entity

ONETOONE
USER and CREDENTIALS onetoone bidirectional relationship
we set user in credentials and credential in user to have the bidirectional work.
userId field will be present in Credentials table.

ONETOMANY and MANYTOONE
USer and Vehicle
Vehicle table will have UserId column.
use mappedby and cascading in User entity.

ONETOMANY
BUDGET AND TRANSACTION join table - BUDGET_TRANSACTION
maintaining a separate column for mapping by using @JoinTable
OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "BUDGET_TRANSACTION", joinColumns = @JoinColumn(referencedColumnName = "ID"),
			inverseJoinColumns = @JoinColumn(referencedColumnName = "ID"))
	private List<Transaction> transactions;
	
MANYTOMANY
ACCOUNT USER
JOIN TABLE USED ACCOUNT_USER

 
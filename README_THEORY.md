## Advantages of LiveData

LiveData is an observable holder for data. An observer can react when a value changes, helping keep the interface consistent with the data and reducing manual refresh logic.

Its lifecycle awareness is a distinct advantage. When an observer is registered with a LifecycleOwner, LiveData delivers updates while that owner is active, in STARTED or RESUMED state. Observers are removed when their owner is destroyed. This reduces the need to manually manage subscriptions for destroyed screens. An observer becoming active again can receive the latest available value.

LiveData can also be exposed from a ViewModel, separating observable screen data from the Activity or Fragment. The ViewModel, rather than LiveData alone, retains its state across configuration changes. Neither replaces persistent storage after process death.

For a hypothetical example, a student list screen could observe a Room query returning LiveData of a list of students. Adding a student would invalidate the query and allow the active screen to display the updated list. If the screen were inactive, its lifecycle-bound observer would not receive active UI updates; upon returning, it could receive the latest data. This example explains LiveData and is not a claim that the supplied application uses it. [1]

## Two ways to navigate with data

An explicit Intent can open another Activity and carry extras, such as a student ID. The destination reads the ID and loads the corresponding record. This approach is useful when screens are separate Activities and integrates with Android's component model. Both sides must agree on the extra's key and type, and the receiving Activity should handle missing or invalid data. [2]

Navigation Compose can pass an argument to a destination within a single-Activity application. For example, a student details route could carry the student ID. Its advantages include a navigation graph, coordinated back-stack handling and direct integration with composable destinations. Passing only an identifier keeps navigation data small and allows the destination to load current details from the database. The present implementation uses Navigation Compose for its screens but takes the search ID from a text field instead of a route argument. [3]

<h1>Simple Search Engine</h1>
<p>
    A basic search engine project that uses <strong>lists</strong>, <strong>inverted indices</strong>, 
    and <strong>binary search trees (BSTs)</strong> to efficiently index, retrieve, and rank documents 
    based on user queries.
</p>

<h2>Features</h2>
<ul>
    <li><strong>Document Indexing</strong>
        <ul>
            <li>Maps document IDs to a list of words (Index).</li>
            <li>Builds an <strong>inverted index</strong> for term-to-document mapping.</li>
            <li>Optimized using <strong>BSTs</strong> for faster retrieval.</li>
        </ul>
    </li>
    <li><strong>Query Processing</strong>
        <ul>
            <li>Supports Boolean queries (<code>AND</code>, <code>OR</code>) for document retrieval.</li>
            <li>Combines result sets efficiently using intersection and union operations.</li>
        </ul>
    </li>
    <li><strong>Document Ranking</strong>
        <ul>
            <li>Implements <strong>term frequency (TF)</strong> to rank documents by relevance.</li>
            <li>Scores documents based on query terms and returns results in descending order.</li>
        </ul>
    </li>
</ul>

<h2>Deliverables</h2>
<ul>
    <li><strong>Index</strong>: Basic mapping of documents to words.</li>
    <li><strong>Inverted Index</strong>: Efficient mapping of words to documents.</li>
    <li><strong>Enhanced Inverted Index with BST</strong>: Improves retrieval performance.</li>
    <li><strong>Query Processor</strong>: Processes Boolean and ranked queries.</li>
    <li><strong>Ranking Algorithm</strong>: Uses term frequency for relevance ranking.</li>
    <li><strong>Performance Analysis</strong>: Compares retrieval efficiency across different index implementations.</li>
    <li><strong>User Interface</strong>: A simple CLI for inputting queries and displaying results.</li>
</ul>

<h2>How It Works</h2>
<ol>
    <li><strong>Document Preprocessing</strong>:
        <ul>
            <li>Reads text documents from a CSV file.</li>
            <li>Cleans and preprocesses text (e.g., removes stopwords, punctuation).</li>
            <li>Converts text to lowercase for uniformity.</li>
        </ul>
    </li>
    <li><strong>Indexing</strong>:
        <ul>
            <li>Creates an index and an inverted index for document retrieval.</li>
            <li>Enhances inverted index with BSTs for faster lookups.</li>
        </ul>
    </li>
    <li><strong>Querying</strong>:
        <ul>
            <li>Boolean queries (<code>AND</code>, <code>OR</code>) return unranked sets of documents.</li>
            <li>Ranked queries use term frequency for relevance-based ordering.</li>
        </ul>
    </li>
    <li><strong>Performance</strong>:
        <ul>
            <li>Measures and compares query processing time for each index type.</li>
            <li>Includes performance analysis in the project report.</li>
        </ul>
    </li>
</ol>

<h2>Usage</h2>
<ol>
    <li>Clone the repository:
        <pre><code>git clone https://github.com/Tarf5100/simple-search-engine.git
cd simple-search-engine</code></pre>
    </li>
    <li>Compile the Java files:
        <pre><code>javac src/*.java</code></pre>
    </li>
    <li>Run the program:
        <pre><code>java src.Main</code></pre>
    </li>
</ol>

<h2>Dependencies</h2>
<p>Ensure you have Java Development Kit (JDK) 8 or higher installed.</p>

<h2>Team Members</h2>
<ul>
  <li>Tarfah Bin Moammar</li>
  <li>Dalal Alyousef</li>
 <li>Manal Alghanmi</li>
</ul>


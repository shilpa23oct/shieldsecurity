// Mobile Menu Toggle
const hamburger = document.getElementById('hamburger');
const navMenu = document.getElementById('navMenu');

hamburger.addEventListener('click', () => {
    navMenu.classList.toggle('active');
    hamburger.classList.toggle('active');
});

// Close menu when clicking on a link
document.querySelectorAll('.nav-link').forEach(link => {
    link.addEventListener('click', () => {
        navMenu.classList.remove('active');
        hamburger.classList.remove('active');
    });
});

// Smooth Scrolling
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();
        const target = document.querySelector(this.getAttribute('href'));
        if (target) {
            const headerOffset = 80;
            const elementPosition = target.getBoundingClientRect().top;
            const offsetPosition = elementPosition + window.pageYOffset - headerOffset;

            window.scrollTo({
                top: offsetPosition,
                behavior: 'smooth'
            });
        }
    });
});

// Navbar Background on Scroll
window.addEventListener('scroll', () => {
    const header = document.querySelector('.header');
    if (window.scrollY > 100) {
        header.style.boxShadow = '0 4px 12px rgba(0,0,0,0.15)';
    } else {
        header.style.boxShadow = '0 4px 6px rgba(0,0,0,0.1)';
    }
});

// Form Submission
const enquiryForm = document.getElementById('enquiryForm');

enquiryForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const submitBtn = e.target.querySelector('button[type="submit"]');
    submitBtn.disabled = true;
    submitBtn.textContent = 'Submitting...';
    
    const enquiryData = {
        name: document.getElementById('name').value,
        phone: document.getElementById('phone').value,
        service: document.getElementById('service').value,
        message: document.getElementById('message').value
    };
    
    try {
        const response = await fetch('/api/enquiry', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(enquiryData)
        });
        
        if (response.ok) {
            const data = await response.json();
            alert('Thank you! Your enquiry has been submitted successfully. We will contact you soon.');
            window.open(data.whatsappUrl, '_blank');
            enquiryForm.reset();
        } else {
            alert('Failed to submit enquiry. Please try again.');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('An error occurred. Please try again.');
    } finally {
        submitBtn.disabled = false;
        submitBtn.textContent = 'Submit Enquiry';
    }
});

// Scroll Animation for Elements
const observerOptions = {
    threshold: 0.1,
    rootMargin: '0px 0px -50px 0px'
};

const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            entry.target.style.opacity = '1';
            entry.target.style.transform = 'translateY(0)';
        }
    });
}, observerOptions);

// Observe service cards, why cards, etc.
document.querySelectorAll('.service-card, .why-card, .about-content').forEach(el => {
    el.style.opacity = '0';
    el.style.transform = 'translateY(30px)';
    el.style.transition = 'opacity 0.6s ease, transform 0.6s ease';
    observer.observe(el);
});

// Active Navigation Link on Scroll
const sections = document.querySelectorAll('section[id]');

window.addEventListener('scroll', () => {
    const scrollY = window.pageYOffset;
    
    sections.forEach(section => {
        const sectionHeight = section.offsetHeight;
        const sectionTop = section.offsetTop - 100;
        const sectionId = section.getAttribute('id');
        
        if (scrollY > sectionTop && scrollY <= sectionTop + sectionHeight) {
            document.querySelectorAll('.nav-link').forEach(link => {
                link.classList.remove('active');
                if (link.getAttribute('href') === `#${sectionId}`) {
                    link.style.color = 'var(--primary-color)';
                } else {
                    link.style.color = '';
                }
            });
        }
    });
});

// Phone number validation
document.getElementById('phone').addEventListener('input', function(e) {
    this.value = this.value.replace(/[^0-9]/g, '');
    if (this.value.length > 10) {
        this.value = this.value.slice(0, 10);
    }
});

// Add loading animation to buttons
document.querySelectorAll('.btn, .btn-service').forEach(button => {
    button.addEventListener('click', function(e) {
        if (!this.classList.contains('loading')) {
            this.style.transform = 'scale(0.95)';
            setTimeout(() => {
                this.style.transform = '';
            }, 150);
        }
    });
});

console.log('Shield Security Services Website Loaded Successfully!');

// Feedback Form Submission
const feedbackForm = document.getElementById('feedbackForm');

if (feedbackForm) {
    feedbackForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const submitBtn = e.target.querySelector('button[type="submit"]');
        submitBtn.disabled = true;
        submitBtn.textContent = 'Submitting...';
        
        const feedbackData = {
            name: document.getElementById('feedbackName').value,
            email: document.getElementById('feedbackEmail').value,
            rating: parseInt(document.getElementById('feedbackRating').value),
            message: document.getElementById('feedbackMessage').value
        };
        
        try {
            const response = await fetch('/api/feedback', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(feedbackData)
            });
            
            if (response.ok) {
                alert('Thank you for your feedback! We appreciate your input.');
                feedbackForm.reset();
            } else {
                alert('Failed to submit feedback. Please try again.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('An error occurred. Please try again.');
        } finally {
            submitBtn.disabled = false;
            submitBtn.textContent = 'Submit Feedback';
        }
    });
}

// Search Functionality
function performSearch() {
    const searchTerm = document.getElementById('searchInput').value.toLowerCase().trim();
    
    if (!searchTerm) {
        alert('Please enter a search term');
        return;
    }
    
    // Clear previous highlights
    clearHighlights();
    
    // Search in page content
    const searchableElements = document.querySelectorAll('h1, h2, h3, h4, p, span, li');
    let found = false;
    
    searchableElements.forEach(element => {
        const text = element.textContent.toLowerCase();
        if (text.includes(searchTerm)) {
            highlightElement(element);
            if (!found) {
                element.scrollIntoView({ behavior: 'smooth', block: 'center' });
                found = true;
            }
        }
    });
    
    if (!found) {
        alert(`No results found for "${searchTerm}"`);
    }
}

function highlightElement(element) {
    element.style.backgroundColor = '#ffd700';
    element.style.padding = '5px';
    element.style.borderRadius = '3px';
    element.classList.add('search-highlight');
}

function clearHighlights() {
    document.querySelectorAll('.search-highlight').forEach(element => {
        element.style.backgroundColor = '';
        element.style.padding = '';
        element.style.borderRadius = '';
        element.classList.remove('search-highlight');
    });
}

// Search on Enter key
document.getElementById('searchInput').addEventListener('keypress', function(e) {
    if (e.key === 'Enter') {
        performSearch();
    }
});
